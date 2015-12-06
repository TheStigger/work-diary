function handleDialogSubmit(args, dialogName) {
    if (!args.validationFailed) {
        PF(dialogName).hide();
    }
}

PrimeFaces.locales['ua'] = {
    firstDay: 1
};

function handleMessage(facesmessage) {
    facesmessage.severity = 'info';

    var audio = new Audio('/audio/tick.mp3');
    audio.play();

    PF('growl').show([facesmessage]);
}
