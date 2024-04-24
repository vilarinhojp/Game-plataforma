package com.uncry.world;

public class Camera {
	public static int x,y;
	
	public static int Clamp(int atual,int min,int max) {
		if(atual>max) {
			atual=max;
		}
		if(atual<min) {
			atual=min;
		}
		return atual;
	}
}
