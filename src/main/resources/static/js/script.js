console.log('hihi')
String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$('.mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect').click(setAssignee);

function setAssignee(e) {
    e.preventDefault();
    var queryString = $('.mdl-menu__item').serialize();
    console.log(queryString)
}
