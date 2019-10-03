package bellintegrator.training.view;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class OrganizationView {

    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public Long id;

    @Size(max = 25)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "name", example = "Газпром")
    public String name;

    @Size(max = 50)
    @NotEmpty(message = "fullName cannot be null")
    @ApiModelProperty(value = "fullName", example = "ООО Газпром")
    public String fullName;

    @Size(max = 15)
    @NotEmpty(message = "inn cannot be null")
    @ApiModelProperty(value = "inn", example = "123456789")
    public String inn;

    @Size(max = 15)
    @NotEmpty(message = "kpp cannot be null")
    @ApiModelProperty(value = "kpp", example = "123454321")
    public String kpp;

    @Size(max = 50)
    @NotEmpty(message = "address cannot be null")
    @ApiModelProperty(value = "address", example = "ул.Цюрупы, 16")
    public String address;

    @Size(max = 15)
    @ApiModelProperty(value = "phone", example = "89603332221")
    public String phone;

    public Boolean isActive = true;

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";fullName" + fullName + ";inn" + inn + ";kpp" + kpp
                + ";address" + address + ";phone" + phone + ";isActive" + isActive + "}";
    }
}
