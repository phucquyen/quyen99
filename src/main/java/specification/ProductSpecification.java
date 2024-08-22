package specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.Product;

public class ProductSpecification implements Specification<Product> {

	private static final long serialVersionUID = 1L;

	private String field;
	private String operator;
	private Object value;

	// ham khoi tao
	public ProductSpecification(String field, String operator, Object value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (operator.equalsIgnoreCase("like")) {
			if (field.equalsIgnoreCase("category")) {
				return criteriaBuilder.like(root.get("category").get("name"), "%" + value.toString() + "%");

			} else {
				return criteriaBuilder.like(root.get(field), "%" + value.toString() + "%");
			}
		}

		return null;
	}

}
