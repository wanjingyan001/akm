package cn.zsmy.akm.widget.call;


/** 
 * @author Stay  
 * @version create time：Apr 22, 2015 5:40:19 PM 
 */
public class PluginEntity {
	public enum PluginType{
		Images,Camera,Sight,VideoCall,LuckyMoney,Transfer,Favorite,Location,NameCard,WalkieTalkie
	}
	public String pluginNameResId;
	public int pluginImgResId;
	public PluginType type;
	public PluginEntity(String pluginNameResId, int pluginImgResId, PluginType type) {
		this.pluginNameResId = pluginNameResId;
		this.pluginImgResId = pluginImgResId;
		this.type = type;
	}
	
	
}
