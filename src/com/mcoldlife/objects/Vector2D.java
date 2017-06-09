package com.mcoldlife.objects;

import org.bukkit.Location;

public class Vector2D {

	int x;
	int y;
	
	public Vector2D(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Location loc){
		
		this.x = loc.getBlockX();
		this.y = loc.getBlockZ();
		
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**Checks if this Vector is between the other Vectors
	 * @param first Vector one
	 * @param second Vector two
	 * @return true if the Vector is between first and second.
	 */
	public boolean betweenVectors(Vector2D first, Vector2D second){
		
		if(this.x > first.getX() && this.x < second.getX()){
			
			if(this.y > first.getY() && this.y < second.getY()){
				
				return true;
				
			}else if(this.y < first.getY() && this.y > second.getY()){
				
				return true;
				
			}
			
		}else if(this.x < first.getX() && this.x > second.getX()){
			if(this.y > first.getY() && this.y < second.getY()){
				
				return true;
				
			}else if(this.y < first.getY() && this.y > second.getY()){
				
				return true;
				
			}
		}
		
		return false;
	}

}
