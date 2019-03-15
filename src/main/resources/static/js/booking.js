let roomTypes;
let currentOptions;

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
    $('#options').text('');

    let request = $.ajax({
        url: '/user/booking/options',
        type: 'GET',
        data: dataToSend,
        dataType: 'json'
    });

    request.done(function (data) {
        console.log(data);
        if (data.status === 'SUCCESS') {
            currentOptions = data.options;
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
        $roomType.find('#demo-img').attr('src', '/static/img/' + roomType.previewImage);
        roomType.bonuses.forEach(function (bonus) {
            $roomType.find('#demo-bonuses')
                .append(
                    '<li class="list-group-item" data-id="' + bonus.id +'">' +
                    bonus.title + ' - <small class="text-muted" id="demo-dates">' + bonus.cost + ' BYN' +
                    '</small></li>'
                )
        });
        $roomType.find('#demo-cost').prepend(roomType.cost);
        $roomType.attr('id', 'option-' + roomType.id);

        let $buttonBasket = $roomType.find('#basket');
        $buttonBasket.attr('id', 'basket-' + roomType.id);
        $buttonBasket.on('click', addToBasket);

        $roomType.on('click', 'li', function () {
            $(this).toggleClass("chosen");
        });

        $roomType.removeAttr('hidden');
        $('#options').append($roomType);
    });

}

function addToBasket() {
    let $this = $(this);
    let roomTypeId = $this.attr('id').substring($this.attr('id').indexOf('-') + 1);
        // ;
    let roomType = roomTypes[roomTypeId];

    let $roomTypeOption = $('#option-' + roomTypeId);
    let $chosenBonuses = $roomTypeOption.find('.chosen');
    let chosenBonusesIds = [];
    $chosenBonuses.each(function (index, value) {
        chosenBonusesIds.push($(value).attr('data-id'));
    });

    let $template = $('#basket-example').clone();
    $template.find('#product-name').append(roomType.title);

    let startDateString = currentOptions[roomTypeId].value.startDate;
    let endDateString = currentOptions[roomTypeId].value.endDate;
    let roomId = currentOptions[roomTypeId].key;

    let startDate = Date.parse(startDateString);
    let endDate = Date.parse(endDateString);
    let days = Math.ceil(Math.abs(endDate - startDate) / (1000 * 60 * 60 * 24));

    $template.find('#product-name').append(' (' + days + ' ночей)');
    let cost = roomType.cost * days;
    console.log($chosenBonuses);
    let chosenBonuses = [];
    chosenBonusesIds.forEach(bonusId => {
        roomType.bonuses.forEach(bonusJSON => {
            if (bonusJSON.id === parseInt(bonusId)) {
                chosenBonuses.push(bonusJSON);
                cost += bonusJSON.cost;
                $template.find('#product-body').append('<small class="text-muted">'
                    + bonusJSON.title + ' - ' + bonusJSON.cost + ' BYN </small><br>')
            }
        })
    });

    $template.find('#product-cost').append(cost + ' BYN');

    let $productCount = $('#product-count');
    let productCount = parseInt($productCount.text(), 10);
    productCount += 1;
    $productCount.text(productCount);

    let $totalCost = $('#total-cost');
    let totalCostText = $totalCost.text();
    $totalCost.text(parseInt(totalCostText.substring(0, totalCostText.indexOf(' '))) + cost);
    $totalCost.append(' BYN');

    $template.removeAttr('hidden');
    $template.removeAttr('id');
    let _csrf = $('#_csrf').attr('name');

    let request = $.ajax({
        url: '/user/booking/add/basket?_csrf=' + _csrf,
        type: 'POST',
        data: JSON.stringify({
            startDate: startDateString,
            endDate: endDateString,
            roomTypeId: parseInt(roomTypeId),
            bonuses: chosenBonuses,
            roomId: roomId,
            cost: cost,
        }),
        dataType: 'json',
        contentType: "application/json"
    });

    request.fail(function (data) {
        alert("FAIL");
    });

    request.done(function (data) {
        alert(data);
    });


    $('#basket-container').prepend($template);
}

function clearBasket() {
    $('#basket-container').html(
        '<li class="list-group-item d-flex justify-content-between">\n' +
        '    <span>Total</span>\n' +
        '    <strong id="total-cost">0 BYN</strong>\n' +
        '</li>'
    );
    return false;
}

function displayAlternatives(alternatives) {

}