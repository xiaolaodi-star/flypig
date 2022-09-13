package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * words
 * @author 
 */
@Data
public class Words implements Serializable {
    private Integer id;

    private String typeofcontent;

    private String content;

    private String txtfilepath;

    private Date createtime;

    private Date updatetime;

    private String builder;

    private String author;

    private static final long serialVersionUID = 1L;
}