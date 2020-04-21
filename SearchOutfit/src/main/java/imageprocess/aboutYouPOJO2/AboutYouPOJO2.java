package imageprocess.aboutYouPOJO2;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AboutYouPOJO2{

	@JsonProperty("pagination")
	private Pagination pagination;

	@JsonProperty("entities")
	private List<EntitiesItem> entities;

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public Pagination getPagination(){
		return pagination;
	}

	public void setEntities(List<EntitiesItem> entities){
		this.entities = entities;
	}

	public List<EntitiesItem> getEntities(){
		return entities;
	}

	@Override
 	public String toString(){
		return 
			"AboutYouPOJO2{" + 
			"pagination = '" + pagination + '\'' + 
			",entities = '" + entities + '\'' + 
			"}";
		}
}
