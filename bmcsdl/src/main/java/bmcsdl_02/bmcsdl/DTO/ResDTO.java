package bmcsdl_02.bmcsdl.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResDTO<T> {
  private T object =  null;
  private String message;
  private String status;
}
