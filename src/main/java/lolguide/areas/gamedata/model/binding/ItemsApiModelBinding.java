package lolguide.areas.gamedata.model.binding;

import java.util.Map;

public class ItemsApiModelBinding {
	
	private Map<String,Data> data;
	
	public ItemsApiModelBinding() {

	}
	
	public ItemsApiModelBinding(Map<String, Data> data) {
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
//		private Map<String,Image> img;
		
		
//		public Data(String name, Map<String, Image> img) {
//			super();
//			this.name = name;
//			this.img = img;
//		}
		public Data(String name) {
			super();
			this.name = name;
		}

		public Data() {
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

//		public Map<String, Image> getImg() {
//			return img;
//		}
//
//		public void setImg(Map<String, Image> img) {
//			this.img = img;
//		}	
//		
//		public String getFullImg() {
//			return this.img.values().toString();
//		}
		
	}
	
//	public static class Image {
//		private String full;
//		
//		public Image() {
//		}	
//
//		public Image(String full) {
//			this.full = full;
//		}
//
//		public String getFull() {
//			return full;
//		}
//
//		public void setFull(String full) {
//			this.full = full;
//		}
//		
//		
//	}
	

}
