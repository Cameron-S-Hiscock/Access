export function accessCall(
    request = "",
    success = () => {},
    failure = () => {},
    module = "access",
) {
    let fullRequest = request.concat(" : ", module);

    window.cefQuery({
        request: fullRequest,
        onSuccess: success,
        onFailure: failure,
    });
}
