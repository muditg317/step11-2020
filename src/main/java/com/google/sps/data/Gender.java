package com.google.sps.data;

public enum Gender {
  AGENDER("Agender"),
  ANDROGYNOUS("Androgynous"),
  BIGENDER("Bigender"),
  DEMIGIRL("Demigirl"),
  DEMIGUY("Demiguy"),
  FEMININE("Feminine"),
  FEMME("Femme"),
  GENDERQUEER("Genderqueer"),
  GENDERFLUID("Genderfluid"),
  INTERSEX("Intersex"),
  MAN("Man"),
  MASCULINE("Masculine"),
  NEUTROIS("Neutrois"),
  NONBINARY("Nonbinary"),
  OTHER("Other"),
  PANGENDER("Pangender"),
  THIRD_GENDER("Third Gender"),
  WOMAN("Woman");

  private String title;

  private Gender(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
