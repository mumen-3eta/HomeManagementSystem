export function boomify(status, message) {
    const error = new Error();//re.data.messages.email[0]
    error.msg = message;
    error.statusError = status;
    throw error;
}
