let roomTypes;

window.onload = function () {

    // запрос списка roomType при загрузке страницы
    $.ajax({
        url: '/user/booking/roomTypes',
        type: 'GET',
        dataType: 'json'
    }).done(function (data) {
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
        alert("Request failed: " + textStatus);
    });
    event.preventDefault();
}

function displayOptions(options) {
    let roomTypeIds = Object.keys(options);

    roomTypeIds.forEach(id => {
        let roomType = roomTypes[id];
        let $roomType = $('#roomTypeHiddenDemo').clone();
        $roomType.find('#demo-title').html(roomType.title);
        $roomType.find('#demo-description').html(roomType.description);
        $roomType.find('#demo-dates').html('с ' + options[id].value.startDate + ' до ' + options[id].value.endDate);
        $roomType.find('#demo-img').attr('src', '/static/img/' +  roomType.previewImage);
        roomType.bonuses.forEach(function (bonus) {
            $roomType.find('#demo-bonuses')
                .append(
                    '<li class="list-group-item">' +
                    bonus.title + ' - <small class="text-muted" id="demo-dates">'+  bonus.cost + ' BYN' +
                    '</small></li>'
                )
        });
        $roomType.find('#demo-cost').prepend(roomType.cost);
        $roomType.attr('id', 'option' + roomType.id);
        $roomType.removeAttr('hidden');

        $roomType.on('click', 'li', function() {
            $(this).toggleClass( "chosen" );
        });


        $('#options').append($roomType);
    });

}

function displayAlternatives(alternatives) {

}