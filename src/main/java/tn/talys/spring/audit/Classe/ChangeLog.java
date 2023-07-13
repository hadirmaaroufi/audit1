package tn.talys.spring.audit.Classe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeLog {
    private String fieldName;
    private String oldValue;
    private String newValue;
}
