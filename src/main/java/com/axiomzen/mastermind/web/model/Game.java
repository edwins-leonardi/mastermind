package com.axiomzen.mastermind.web.model;

import java.util.Date;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

	private Color[] colorsCode;
	private Date creationTime;
	private Boolean solved;

	public Game() {
		this.creationTime = new Date();
		createColorsCode();
		solved = false;
	}

	private void createColorsCode() {
		Color[] colors = Color.values();
		colorsCode = new Color[colors.length];
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < colors.length; i++) {
			colorsCode[i] = colors[random.nextInt(colors.length)];
			System.out.println(colorsCode[i]);
		}
	}
}
