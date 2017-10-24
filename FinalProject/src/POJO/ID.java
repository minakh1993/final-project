package POJO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;




	
	@Embeddable
	public class ID implements Serializable {

		@Column(name = "name", nullable = false, length = 20)
		protected String name;

		@Column(name = "family", nullable = false, length = 30)
		protected String family;

		public ID() {
		
		}
		
		public ID(String name, String family) {
			this.name = name;
			this.family = family;
		}

		
		

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFamily() {
			return family;
		}

		public void setFamily(String family) {
			this.family = family;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			
			result = prime * result + ((family == null) ? 0 : family.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
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
			ID other = (ID) obj;

			if (family == null) {
				if (other.family != null)
					return false;
			} else if (!family.equals(other.family))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		
	}

