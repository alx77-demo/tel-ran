package com.telran.cheaper.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.telran.cheaper.MongoConfig;
import com.telran.cheaper.model.Place;

//@Component
@Repository
public class PlaceRepository {

	static final Logger log = LoggerFactory.getLogger(PlaceRepository.class);

	@Value("${name:World}")
	private String name;

	@Autowired
	MongoTemplate mongoTemplate;

	public List<Place> getPlacesNear(Point point, Distance distance) {
		// Query query = new Query();
		// Criteria criteria = new Criteria();
		// criteria = criteria.and("age").lte(21);
		//
		// query.addCriteria(criteria);
		// // results = mongoTemplate.find(query, Place.class);
		// long count = this.mongoTemplate.count(query, Place.class);

		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class)) {
			// MongoOperations mongoTemplate = (MongoOperations)
			// ctx.getBean("mongoTemplate");

			// Place p = new Place();
			// p.setLikes(10);
			// p.setPricerange("Moderate");
			// p.setSlug("my-first-place");
			// p.setPublicdate(LocalDate.now());
			// p.setType("zakusochnaja");
			// p.setDislikes(3);
			// p.setRate(3.6);
			// Point point = new Point(3.5, 4.5);
			// p.setLocation(new double[] { point.getX(), point.getY() });
			// mongoTemplate.save(p);
			// res += p;

			// NearQuery query =
			// NearQuery.near(point.getX(),point.getY()).spherical(true).maxDistance(maxDistance,Metrics.MILES).distanceMultiplier(Metrics.MILES).query(regularQuery);//maxDistance(new
			// Distance(radius,Metrics.MILES));
			// GeoResults<CalendarEvent> results =
			// ((MongoOperations)mongoTemplate).geoNear(query,
			// CalendarEvent.class);

			// mongoTemplate.indexOps(Place.class).ensureIndex(new
			// GeospatialIndex("attribute.holding.coordinates"));
			// NearQuery nearQuery = NearQuery.near(x, y);
			// final GeoResults<Place> geoResults =
			// mongoTemplate.geoNear(nearQuery, Place.class);
			//// return
			// geoResults.getContent().stream().map(GeoResult::getContent).collect(Collectors.toList());
			// BasicQuery bq = new BasicQuery("");
			// query.addCriteria(timeCriteria);
			// query.fields().exclude("friends");

			// Integer resultLimit = 20; // Limit to 20 records
			Criteria geoCriteria = Criteria.where("location").withinSphere(new Circle(point, distance));
			// Criteria timeCriteria = Criteria.where("time").gte(new Date());
			Query query = Query.query(geoCriteria);
			// BasicQuery bq = new BasicQuery("");
			// query.addCriteria(timeCriteria);
			// query.fields().exclude("friends");

			List<Place> npl = mongoTemplate.find(query, Place.class);
			return npl;
		}
	}

	/**
	 * This will count how many Place Objects I have
	 */
	public long countAllPlaces() {
		// findAll().size() approach is very inefficient, since it returns the
		// whole documents

		long total = this.mongoTemplate.count(null, Place.class);
		// log.info("Total number in database: {}", total);

		return total;
	}

	/**
	 * this will create a {@link Place} collection if the collection does not
	 * already exists
	 */
	public void createPlaceCollection() {
		if (!mongoTemplate.collectionExists(Place.class)) {
			mongoTemplate.createCollection(Place.class);
		}
	}

	/**
	 * this will drop the {@link Place} collection if the collection does
	 * already exists
	 */
	public void dropPlaceCollection() {
		if (mongoTemplate.collectionExists(Place.class)) {
			mongoTemplate.dropCollection(Place.class);
		}
	}
}