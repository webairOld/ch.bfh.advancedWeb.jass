package ch.frickler.jass.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ch.frickler.jass.db.enums.CardFamily;
import ch.frickler.jass.db.enums.CardValue;
import ch.frickler.jass.db.enums.GameKind;

/**
 * Entity implementation class for Entity: Card
 * 
 */
@Entity
@Table(name = "CARD")
public class Card implements Serializable, Comparable<Card> {

	private static final long serialVersionUID = 1L;

	public static final int TOTALCARD = 36;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CARD")
	private long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "FAMILY", nullable = false)
	private CardFamily family;
	@Enumerated(EnumType.STRING)
	@Column(name = "VALUE", nullable = false)
	private CardValue value;

	public Card(CardFamily family, CardValue value) {
		super();
		this.family = family;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Card() {
		super();
	}

	public CardFamily getFamily() {
		return family;
	}

	public void setFamily(CardFamily family) {
		this.family = family;
	}

	public CardValue getValue() {
		return value;
	}

	public void setValue(CardValue value) {
		this.value = value;
	}

	public int getOrderValue() {

		switch (value) {
		case SECHS:
			return 6;
		case SIEBEN:
			return 7;
		case ACHT:
			return 8;
		case NEUN:
			return 9;
		case ZEHN:
			return 10;
		case BAUER:
			return 11;
		case DAME:
			return 12;
		case KOENIG:
			return 13;
		case ASS:
			return 14;
		}

		return 0;
	}

	@Override
	public int compareTo(Card o) {

		if (o == null)
			return -1;

		if (o.equals(this))
			return 0;

		// same family - sort by order value
		if (o.getFamily().equals(this.getFamily()))
			return this.getOrderValue() - o.getOrderValue();

		// different families...
		// we want the following order: herz, kreuz, ecke, schaufel
		return getFamilyOrder(o.getFamily()) - getFamilyOrder(this.getFamily());

	}

	private int getFamilyOrder(CardFamily f) {
		int ret = 0;
		if (CardFamily.HERZ.equals(f)) {
			ret = 4;
		} else if (CardFamily.KREUZ.equals(f)) {
			ret = 3;
		} else if (CardFamily.ECKEN.equals(f)) {
			ret = 2;
		} else if (CardFamily.KREUZ.equals(f)) {
			ret = 1;
		}
		return ret;
	}

}
