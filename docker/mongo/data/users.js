db = new Mongo().getDB("app");
db.persons.createIndex(
    {
        "id": 1
    },
    {
        unique: true
    }
)

db.persons.insert({
    id: 1,
    firstName: "Dave",
    lastName: "Goodin",
    age: 49,
    isFunny: true,
    gender: {type: "Male"}
});
db.persons.insert({
    id: 2,
    firstName: "Nicole",
    lastName: "Goodin",
    age: 47,
    isFunny: true,
    gender: {type: "Female"}
});
db.persons.insert({
    id: 3,
    firstName: "Corey",
    lastName: "Goodin",
    age: 28,
    isFunny: true,
    gender: {type: "Male"}
});
db.persons.insert({
    id: 4,
    firstName: "Kylie",
    lastName: "Goodin",
    age: 23,
    isFunny: true,
    gender: {type: "Female"}
});