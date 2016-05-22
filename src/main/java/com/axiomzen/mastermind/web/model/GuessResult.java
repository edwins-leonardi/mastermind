package com.axiomzen.mastermind.web.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class GuessResult {
	private Integer exact;
	private Integer near;

	public GuessResult() {
		this.exact = 0;
		this.near = 0;
	}

	public GuessResult(int exact, int near) {
		this.exact = exact;
		this.near = near;
	}

	public void addExactScore() {
		exact = exact + 1;
	}

	public void addNearScore() {
		near = near + 1;
	}

}
