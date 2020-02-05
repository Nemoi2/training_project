package bellintegrator.training.view;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class OrganizationsView {

    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public Long id;

    @Size(max = 25)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "name", example = "Газпром")
    public String name;

    @JsonView(View.ListOtput.class)
    @Size(max = 15)
    @ApiModelProperty(value = "inn", hidden = true, example = "123456789")
    public String inn;

    public Boolean isActive = true;
}
