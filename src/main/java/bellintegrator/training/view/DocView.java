package bellintegrator.training.view;

import io.swagger.annotations.ApiModelProperty;

public class DocView {

    @ApiModelProperty(value = "docName", example = "Военный билет")
    public String name;

    @ApiModelProperty(value = "Уникальный идентификатор", example = "7")
    public Long code;
}
