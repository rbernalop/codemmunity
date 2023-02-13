import moment from "moment";

export const checkRequiredField = (message) => {
    return {
        required: true,
        message: message
    }
}

export const checkEmail = () => {
    return {
        type: 'email',
        message: 'The input is not a valid email!'
    }
}

export const checkPasswordsMatch = ({ getFieldValue }) => ({
    validator(rule, value) {
        if (!value || getFieldValue('password') === value) {
            return Promise.resolve();
        }
        return Promise.reject(
            'The two passwords that you entered do not match!'
        );
    }
})

export const checkAgeIsOver18 = ({ getFieldValue }) => ({
    validator(rule, value) {
        if (!value || moment().diff(value.format("YYYY-MM-DD"), 'years') >= 18) {
            return Promise.resolve();
        }
        return Promise.reject(
            'You must be over 18 years old to register!'
        );
    }
})