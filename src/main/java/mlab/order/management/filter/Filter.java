package mlab.order.management.filter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mlab.order.management.model.enums.QueryOperator;

import java.util.List;

@Getter
@Setter
@Builder
public class Filter {
    private String field;
    private QueryOperator operator;
    private String value;
    private List<String> values;

}
