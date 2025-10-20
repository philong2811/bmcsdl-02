CREATE OR REPLACE FUNCTION record_renewal_by_user(
    p_schema in varchar2 default null,
    p_object in varchar2 default null
) return varchar2
as
    v_role varchar2(10);
    v_user_cmnd varchar2(10);
begin
    select u.role, u.cmnd into v_role, v_user_cmnd
    from users u where u.username = SYS_CONTEXT('USERENV', 'SESSION_USER');
    if v_role = 'USER' then
        return 'CMND = ''' || v_user_cmnd || '''';
    end if;
    return '1=1';
end;
/

begin
    dbms_rls.add_policy(
        object_schema => 'ADMIN_TEST',
        object_name => 'RENEWAL',
        policy_name => 'USER_SELECT_YOURSELF',
        policy_function => 'record_renewal_by_user',
        statement_types => 'SELECT'
    );
end;
/

create or replace function no_record_by_user(
    p_schema in varchar2 default null,
    p_object in varchar2 default null
) return varchar2
as
    v_role varchar2(10);
begin
    select u.role into v_role
    from users u where u.username = SYS_CONTEXT('USERENV', 'SESSION_USER');
    if v_role = 'USER' then
        return '1=0';
    end if;
    return '1=1';
end;
/

begin
    dbms_rls.add_policy(
        object_schema => 'ADMIN_TEST',
        object_name => 'RESIDENT',
        policy_name => 'USER_NO_RECORD_RESIDENT',
        policy_function => 'no_record_by_user',
        statement_types => 'SELECT'
    );
end;
/

select * from all_users;