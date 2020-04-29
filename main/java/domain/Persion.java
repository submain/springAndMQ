package domain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
public class Persion implements InitializingBean,DisposableBean{
	@Value("${person.nickName}")
    String name;
    String address;
    
    
    public Persion(){
    	
    }
    
    public Persion(String name,String address){
    	this.name=name;
    	this.address=address;
    }

	@Override
	public String toString() {
		return "Persion [name=" + name + ", address=" + address + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("³õÊ¼»¯"+this.getClass());
		
	}

	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Ïú»Ù"+this.getClass());
		
	}
	
	
    
}
