let roomTypes;

window.onload = function() {
    let request = $.ajax({
        url: '/user/booking/roomTypes',
        type: 'GET',
        dataType: 'json'
    });

    request.done(function (data) {
        roomTypes = data;
    });
};

$('#filterForm').submit(function (event) {
    let isFormValid = true;

    $('#login-form input[type="text"], #login-form input[type="number"]').each(function () {
        if (this.checkValidity()) {
            $(this).removeClass('invalid').next().fadeOut(100);
        } else {
            $(this).addClass('invalid').next().fadeIn(100);
            isFormValid = false
        }
    });
    if (isFormValid) {
        getOptionsJSON(this);
    } else {
        event.preventDefault();
    }
});

function getOptionsJSON(form) {
    let dataToSend;
    dataToSend = $(form).serialize();

    let request = $.ajax({
        url: '/user/booking/options',
        type: 'GET',
        data: dataToSend,
        dataType: 'json'
    });

    request.done(function (data) {
        console.log(data);
        if (data.status === 'SUCCESS') {
            displayOptions(data.options);
        } else if (data.status === 'WARNING') {
            displayAlternatives(data.alternatives);
        } else {
            alert(data.message);
        }
    });
    
    request.fail(function (jqXHR, textStatus) {
        alert( "Request failed: " + textStatus );
    });
    event.preventDefault();
}

function displayOptions(options) {
    let roomTypeIds = Object.keys(options);
    roomTypeIds.map(id => {
        let roomType = roomTypes[id];
        let result = `<div class="col-md-4 mt-5">\n` +
        `                    <div class="card">\n` +
        `                        <img src="${roomType.previewImage}" class="card-img-top">\n` +
        `                        <div class="card-body">\n` +
        `                            <h5 class="card-title">${roomType.title}</h5>\n` +
        `                            <p class="card-text">${roomType.description}</p>\n` +
        `                        </div>\n` +
        `                        <ul class="list-group list-group-flush">\n`;
        roomType.bonuses.foreach(function (bonus) {
            result += `<li class="list-group-item">${bonus.title} - ${bonus.cost}</li>\n`
        });
        result += `</ul>\n` +
        `                    </div>\n` +
        `                </div>`

        return result;
    });
}

function displayAlternatives(alternatives) {

}