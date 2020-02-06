let f = debounce(message, 1000);

f(1);
f(2);

function message(m) {
    console.log(m);
}

setTimeout(()=>f(3), 100);
setTimeout(()=>f(4), 1100);
setTimeout(()=>f(5), 1500);


function debounce(func, ms) {
    let isCooldown = false;
    function wrapper(x) {
        // check time lapse ms after the previous call
        if(isCooldown) return;

        func.call(this, x);
        isCooldown = true;

        setTimeout(()=>isCooldown=false, ms);
    }
    return wrapper;
}