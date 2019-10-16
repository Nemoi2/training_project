package bellintegrator.training.view;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeListView {

    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public Long id;

    @JsonView(View.ListOtput.class)
    @ApiModelProperty(value = "Уникальный идентификатор организации", example = "1")
    @NotNull
    public Long officeId;

    @Size(max = 25)
    @ApiModelProperty(value = "firstName", example = "Kozlov")
    public String firstName;

    @Size(max = 25)
    @ApiModelProperty(value = "secondName", example = "Andrey")
    public String secondName;

    @Size(max = 25)
    @ApiModelProperty(value = "middleName", example = "Sergeevich")
    public String middleName;

    @Size(max = 25)
    @ApiModelProperty(value = "position", example = "administrator")
    public String position;

    @JsonView(View.ListOtput.class)
    @ApiModelProperty(value = "docName", example = "21")
    public Long docCode;

    @JsonView(View.ListOtput.class)
    @ApiModelProperty(value = "citizenshipCode", example = "380")
    public Long citizenshipCode;
}
