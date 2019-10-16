package bellintegrator.training.view;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class EmployeesView {

    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public Long id;

    @JsonView(View.ListOtput.class)
    @ApiModelProperty(value = "Уникальный идентификатор организации", example = "1")
    @NotNull
    public Long officeId;

    @Size(max = 25)
    @NotEmpty(message = "firstName cannot be null")
    @ApiModelProperty(value = "firstName", example = "Kozlov")
    public String firstName;

    @Size(max = 25)
    @ApiModelProperty(value = "secondName", example = "Andrey")
    public String secondName;

    @Size(max = 25)
    @ApiModelProperty(value = "middleName", example = "Sergeevich")
    public String middleName;

    @Size(max = 25)
    @NotEmpty(message = "position cannot be null")
    @ApiModelProperty(value = "position", example = "administrator")
    public String position;

    @JsonView(View.ListOtput.class)
    @Size(max = 15)
    @ApiModelProperty(value = "phone", example = "89603332221")
    public String phone;

    @JsonView(View.ListOtput.class)
    @ApiModelProperty(value = "docName", example = "21")
    public Long docCode;

    @JsonView(View.ListOtput.class)
    @Size(max = 40)
    @ApiModelProperty(value = "docName", example = "Паспорт гражданина РФ")
    public String docName;

    @JsonView(View.ListOtput.class)
    @Size(max = 15)
    @ApiModelProperty(value = "docNumber", example = "123454321")
    public String docNumber;

    @JsonView(View.ListOtput.class)
    @ApiModelProperty(value = "docDate", example = "2019-09-12")
    public Date docDate;

    @JsonView(View.ListOtput.class)
    @ApiModelProperty(value = "citizenshipCode", example = "380")
    public Long citizenshipCode;

    @JsonView(View.ListOtput.class)
    public Boolean isIdentified = true;
}
