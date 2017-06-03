db.createUser({
	user:"alx",
	pwd:"securepass",
	roles:["readWrite","dbAdmin"]
});
--
db.createCollection('places');
db.places.insert([
{
slug:"sosage-restaurant-city-street-housenum-timestamp",
publicdate:"1/1/2021",
userid:[new ObjectId(123456789012345678901234)],
name:[{lang:en, name:"sosage"}]
address:[]
location:[]
phones:[]
type:"restaurant";//fastfood poleznaya eda
kitchen:["french", "falafel", "arabian"]
rate:4.5
votes:123
likes:12
dislikes:33
sites: ["http://mail.ru","http://i-ween.com"]
features:["freewifi","paywithcard"]
schedule:[{[{[monday,tuesday]},{["8:00-12:00","13:00-16:00"]}]},{[{[friday,sunday]},{["8:00-10:00","11:00-13:00"]}]}]
pricerange:"moderate"
near:{busstation:["sdda"],rakevet:["asd","qwe"],metro:["wqe","ewr"]}
},
], safe:true);
db.places.createIndex({slug:1}, {unique:true});
db.places.find().skip((page_number-1)*10).limit(10).sort({helpful_votes:-1});


db.createCollection('reviews');
db.reviews.insert([
{
publicdate:"1/1/2021"
userid:1
mark:{kitchen:1.0, interier:1.0, service:1.0}
brieftext:""
linktofulltext:""
likes:12
dislikes:33
votes:[{useful:1,fynny:0,cool:2}]
},
]);

db.createCollection('photos');
db.photos.insert([
{
publicdate:"1/1/2021"
userid:1
reviewid:0
answeronreview:0
caption:"my best geronimo"
photo:["/pictures/img123.jpg"]
likes:12
dislikes:33
},
]);

db.createCollection('users');
db.reviews.insert([
{
name:"Vasia Pupkin"
email:""
password:""
currentlocation:[]
lastcheckindate:""
},
]);
