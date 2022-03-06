package lesson5hw.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("categoryTitle")
    private String categoryTitle;
    @JsonProperty("status")
    private String status;
}
