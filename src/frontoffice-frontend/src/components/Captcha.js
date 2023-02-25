import ReCAPTCHA from "react-google-recaptcha";

const Captcha = ({ captchaRef }) => {
    return (
        <div style={{ display: 'flex', justifyContent: 'center' }}>
            <ReCAPTCHA sitekey={process.env.REACT_APP_CAPTCHA_KEY} ref={captchaRef} />
        </div>
    );
}

export default Captcha;