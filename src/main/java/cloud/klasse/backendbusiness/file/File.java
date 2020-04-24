package cloud.klasse.backendbusiness.file;


import cloud.klasse.backendbusiness.result.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "File")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String alt;

    private byte[] content;

    private String mimeType;

    private Result resultId;
}
