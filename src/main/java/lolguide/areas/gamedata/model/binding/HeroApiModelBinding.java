package lolguide.areas.gamedata.model.binding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeroApiModelBinding {
	
//	@JsonProperty("data")
	private Map<String, Data> data;
	
	public HeroApiModelBinding() {
	}
	
	public HeroApiModelBinding(Map<String, Data> data) {
		super();
		this.data = data;
	}

	public Map<String, Data> getData() {
		return data;
	}


	public void setData(Map<String, Data> data) {
		this.data = data;
	}

	public static class Data{
		
		private String name;
		private String img;
		private String blurb;
		private String title;
		private List<String> tags = new ArrayList<>();
		
		public Data() {
		}		
		

		public Data(String name, String img, String blurb, String title, List<String> tags) {
			this.name = name;
			this.img = img;
			this.blurb = blurb;
			this.title = title;
			this.tags = tags;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getImg() {
			return img;
		}


		public void setImg(String img) {
			this.img = img;
		}


		public String getBlurb() {
			return blurb;
		}


		public void setBlurb(String blurb) {
			this.blurb = blurb;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public List<String> getTags() {
			return tags;
		}


		public void setTags(List<String> tags) {
			this.tags = tags;
		}

		
		
		
	}
	
	
	
	
}

