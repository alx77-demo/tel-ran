package com.telran.cheaper.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Document(collection = "places")
public class Place {
	private @Id String id;
	String slug;
	LocalDate publicdate;
	String userid;
	String type;//"restaurant";fastfood poleznaya eda
//	name:[{lang:en, name:"sosage"}]
	String[] addr;
	String[] phones;
	@GeoSpatialIndexed
	double[] location;
//	kitchen:["french", "falafel", "arabian"]
	Double rate;//:4.5
	Integer votes;//:123
	Integer likes;//:12
	Integer dislikes;//:33
	//sites: ["http://mail.ru","http://i-ween.com"]
	//features:["freewifi","paywithcard"]
	//schedule:[{[{[monday,tuesday]},{["8:00-12:00","13:00-16:00"]}]},{[{[friday,sunday]},{["8:00-10:00","11:00-13:00"]}]}]
	String pricerange;//:"moderate"
	//near:{busstation:["sdda"],rakevet:["asd","qwe"],metro:["wqe","ewr"]}

}
