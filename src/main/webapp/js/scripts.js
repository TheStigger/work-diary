function handleDialogSubmit(args, dialogName) {
    if (!args.validationFailed) {
        PF(dialogName).hide();
    }
}

PrimeFaces.locales['ua'] = {
    firstDay: 1
};
