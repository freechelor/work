let user = {
    name : "John",
    toString() {
        return this.name;
    }
};

Object.defineProperty(user, "toString", {enumerable:false});
// for(let key in user) console.log(key);
let clone={};
for(let key in user) {
    clone[key] = user[key];
}

console.log(clone);
console.log(Object.getOwnPropertyDescriptors(clone));