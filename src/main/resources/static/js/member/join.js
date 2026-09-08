document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("joinForm");
    const loginId = document.getElementById("loginId");
    const duplicateIdCheckButton = document.getElementById("duplicateIdCheckButton");
    const loginIdFeedback = document.getElementById("loginIdFeedback");
    const password = document.getElementById("password");
    const passwordCheck = document.getElementById("passwordCheck");
    const passwordCheckFeedback = document.getElementById("passwordCheckFeedback");
    const emailAccount = document.getElementById("emailAccount");
    const emailDomain = document.getElementById("emailDomain");
    const customDomainGroup = document.getElementById("customDomainGroup");
    const customEmailDomain = document.getElementById("customEmailDomain");
    const email = document.getElementById("email");
    const emailFeedback = document.getElementById("emailFeedback");
    const phoneDisplay = document.getElementById("phoneDisplay");
    const phone = document.getElementById("phone");

    const setLoginIdFeedback = (message, status) => {
        loginIdFeedback.textContent = message;
        loginIdFeedback.className = status === "success" ? "valid-feedback d-block" :
            status === "error" ? "invalid-feedback d-block" : "form-text";
        loginId.classList.toggle("is-valid", status === "success");
        loginId.classList.toggle("is-invalid", status === "error");
    };

    const validatePasswordMatch = () => {
        if (!passwordCheck.value) {
            passwordCheck.setCustomValidity("비밀번호 확인을 입력해 주세요.");
            passwordCheck.classList.remove("is-valid");
            passwordCheckFeedback.className = "invalid-feedback";
            passwordCheckFeedback.textContent = "비밀번호를 다시 입력해 주세요.";
            return false;
        }
        if (password.value !== passwordCheck.value) {
            passwordCheck.setCustomValidity("비밀번호가 일치하지 않습니다.");
            passwordCheck.classList.remove("is-valid");
            passwordCheck.classList.add("is-invalid");
            passwordCheckFeedback.className = "invalid-feedback";
            passwordCheckFeedback.textContent = "비밀번호가 일치하지 않습니다.";
            return false;
        }
        passwordCheck.setCustomValidity("");
        passwordCheck.classList.remove("is-invalid");
        passwordCheck.classList.add("is-valid");
        passwordCheckFeedback.className = "valid-feedback";
        passwordCheckFeedback.textContent = "비밀번호가 일치합니다.";
        return true;
    };

    const updateEmail = () => {
        const isCustomDomain = emailDomain.value === "custom";
        customDomainGroup.classList.toggle("d-none", !isCustomDomain);
        customEmailDomain.required = isCustomDomain;
        const domain = isCustomDomain ? customEmailDomain.value.trim() : emailDomain.value;
        const value = emailAccount.value.trim() && domain ? `${emailAccount.value.trim()}@${domain}` : "";
        const isValid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);

        email.value = value;
        emailAccount.setCustomValidity(value && !isValid ? "올바른 이메일을 입력해 주세요." : "");
        customEmailDomain.setCustomValidity(isCustomDomain && value && !isValid ? "올바른 도메인을 입력해 주세요." : "");
        emailFeedback.textContent = isValid ? value : "계정과 도메인을 선택해 이메일을 완성해 주세요.";
        emailFeedback.className = isValid ? "valid-feedback d-block" : "form-text";
        return isValid;
    };

    const formatPhoneNumber = () => {
        const digits = phoneDisplay.value.replace(/\D/g, "").slice(0, 11);
        let formatted = digits;
        if (digits.length > 7) formatted = `${digits.slice(0, 3)}-${digits.slice(3, 7)}-${digits.slice(7)}`;
        else if (digits.length > 3) formatted = `${digits.slice(0, 3)}-${digits.slice(3)}`;
        phoneDisplay.value = formatted;
        phone.value = digits;
        phoneDisplay.setCustomValidity(digits.length === 0 || digits.length === 11 ? "" : "전화번호 11자리를 입력해 주세요.");
    };

    loginId.addEventListener("input", () => setLoginIdFeedback("로그인 ID 변경 후 중복확인이 필요합니다.", ""));
    password.addEventListener("input", validatePasswordMatch);
    passwordCheck.addEventListener("input", validatePasswordMatch);
    emailAccount.addEventListener("input", updateEmail);
    emailDomain.addEventListener("change", updateEmail);
    customEmailDomain.addEventListener("input", updateEmail);
    phoneDisplay.addEventListener("input", formatPhoneNumber);

    duplicateIdCheckButton.addEventListener("click", async () => {
        const id = loginId.value.trim();
        if (!id) {
            setLoginIdFeedback("로그인 ID를 먼저 입력해 주세요.", "error");
            return;
        }

        duplicateIdCheckButton.disabled = true;
        setLoginIdFeedback("로그인 ID 중복 여부를 확인하고 있습니다.", "");
        try {
            const response = await fetch(`/member/duplicateIdCheck?id=${encodeURIComponent(id)}`, {
                method: "GET"
            });
            if (!response.ok) throw new Error("Duplicate ID check endpoint is unavailable");

            setLoginIdFeedback("중복확인 API의 응답 처리 방식이 아직 정의되지 않았습니다.", "error");
        } catch (error) {
            setLoginIdFeedback("현재 로그인 ID 중복확인 기능을 사용할 수 없습니다.", "error");
        } finally {
            duplicateIdCheckButton.disabled = false;
        }
    });

    form.addEventListener("submit", (event) => {
        const passwordMatches = validatePasswordMatch();
        const emailIsValid = updateEmail();
        formatPhoneNumber();
        if (!passwordMatches || !emailIsValid || !form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }
        form.classList.add("was-validated");
    });
});
