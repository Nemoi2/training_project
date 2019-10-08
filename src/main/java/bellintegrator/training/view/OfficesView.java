package bellintegrator.training.view;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OfficesView {

    @JsonView(View.ListOtput.class)
    @ApiModelProperty(value = "Уникальный идентификатор организации", example = "1")
    @NotNull
    public Long orgId;

    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public Long id;

    @Size(max = 25)
    @ApiModelProperty(value = "name", example = "Газпром")
    public String name;

    @JsonView(View.ListOtput.class)
    @Size(max = 50)
    @ApiModelProperty(value = "address", example = "ул.Цюрупы, 16")
    public String address;

    @JsonView(View.ListOtput.class)
    @Size(max = 15)
    @ApiModelProperty(value = "phone", example = "89603332221")
    public String phone;

    public Boolean isActive = true;
}
