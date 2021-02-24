package equipamentconnection;

import etherip.types.CIPData;
import etherip.types.CIPData.Type;

public class Tag {
	private String name;
	private CIPData.Type type;
	private Number value;

	public Tag() {

	}

	public Tag(String name, Type type, Number value) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public CIPData.Type getType() {
		return type;
	}

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tags [name=" + name + ", type=" + type + ", value=" + value + "]";
	}

}
