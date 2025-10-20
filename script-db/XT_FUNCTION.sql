-- function renewal--
CREATE OR REPLACE FUNCTION XT_DISTRICT_POLICY(
    p_schema IN VARCHAR2 DEFAULT NULL,
    p_object IN VARCHAR2 DEFAULT NULL
) RETURN VARCHAR2
IS
    v_user_district VARCHAR2(10);
    v_user_role VARCHAR2(10);
BEGIN    
    SELECT u.DISTRICT, u.ROLE INTO v_user_district, v_user_role
    FROM ADMIN_TEST.USERS u WHERE USERNAME = SYS_CONTEXT('USERENV', 'SESSION_USER');
    -- Chỉ áp dụng cho user XT
    IF v_user_role = 'XT' THEN
        RETURN 'DISTRICT = ''' || v_user_district || '''';
    END IF;
    RETURN '1=1'; -- Admin và role khác xem tất cả
END;
/

select  XT_DISTRICT_POLICY() from dual;

---POLICY CHO BANG RESIDENT--
BEGIN
    DBMS_RLS.ADD_POLICY(
        object_schema => 'ADMIN_TEST',
        object_name   => 'RESIDENT',
        policy_name   => 'XT_SELECT_RESIDENT',
        policy_function => 'XT_DISTRICT_POLICY',
        statement_types => 'SELECT'
    );
END;
/

---POLICY CHO BANG RENEWAL--
BEGIN
    DBMS_RLS.ADD_POLICY(
        object_schema => 'ADMIN_TEST',
        object_name   => 'RENEWAL',
        policy_name   => 'XT_SELECT_UPDATE_RENEWAL',
        policy_function => 'XT_DISTRICT_POLICY',
        statement_types => 'SELECT, UPDATE',
        update_check => TRUE
    );
END;
/

 -- Trigger chỉ cho phép cập nhật cột STATUS---
CREATE OR REPLACE TRIGGER TRG_RENEWAL_ONLY_STATUS
    BEFORE UPDATE ON ADMIN_TEST.RENEWAL
    FOR EACH ROW
DECLARE
    v_user_role VARCHAR2(255);
    v_user_name VARCHAR2(255) := SYS_CONTEXT('USERENV', 'SESSION_USER');
BEGIN
    -- Lấy role của user hiện tại
    SELECT ROLE INTO v_user_role
    FROM ADMIN_TEST.USERS 
    WHERE USERNAME = v_user_name;
    -- Nếu user là XT, chỉ cho phép update STATUS
    IF v_user_role = 'XT' THEN
        IF :OLD.CMND != :NEW.CMND OR
           :OLD.DISTRICT != :NEW.DISTRICT OR
           :OLD.CREATE_DATE != :NEW.CREATE_DATE OR
           :OLD.VERIFIED_BY != :NEW.VERIFIED_BY THEN
            RAISE_APPLICATION_ERROR(-20001, 'User XT chỉ được phép cập nhật cột STATUS');
        END IF;
    END IF;
END;

ALTER TRIGGER TRG_RENEWAL_ONLY_STATUS COMPILE;

CREATE OR REPLACE FUNCTION XT_ONLY_STATUS(
    p_schema IN VARCHAR2 DEFAULT NULL,
    p_object IN VARCHAR2 DEFAULT NULL
) RETURN VARCHAR2
AS
    v_user_role VARCHAR2(10);
BEGIN
    SELECT u.ROLE INTO v_user_role
    FROM USERS u WHERE username = SYS_CONTEXT('USERENV', 'SESSION_USER'); 
    IF v_user_role = 'XT' THEN
        RETURN 'STATUS = ''Đang chờ xử lý''';
    END IF;
    RETURN '1=1';
END;
/

BEGIN
    dbms_rls.add_policy(
        object_schema => 'ADMIN_TEST',
        object_name   => 'RENEWAL',
        policy_name   => 'XT_SELECT_UPDATE_STATUS_RENEWAL',
        policy_function => 'XT_ONLY_STATUS',
        statement_types => 'SELECT, UPDATE',
        update_check => FALSE
    );
END;
/

BEGIN
    dbms_rls.drop_policy(
        object_schema => 'ADMIN_TEST',
        object_name   => 'RENEWAL',
        policy_name   => 'XT_SELECT_UPDATE_STATUS_RENEWAL'
    );
END;
/