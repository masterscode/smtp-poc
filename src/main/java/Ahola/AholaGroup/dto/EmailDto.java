package Ahola.AholaGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    private String sender;
    private String recipient;

    private String messageBody;

    private String subject;

    private String attachment;

}
