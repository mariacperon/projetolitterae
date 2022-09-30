function usarApi(method, url, json) {
    return new Promise(function (resolve, reject) {
        let xhr = new XMLHttpRequest();
        xhr.open(method, url);
        if (json!=undefined){
            xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        }
        xhr.onload = function () {
            if (this.status >= 200 && this.status < 300) {
                resolve(xhr.response);
            } else {
                reject({
                    status: this.status,
                    statusText: xhr.statusText
                });
            }
        };
        xhr.onerror = function () {
            reject({
                status: this.status,
                statusText: xhr.statusText
            });
        };
        if (json!=undefined){
            xhr.send(json);
        }else{
            xhr.send();
        }
    });
}