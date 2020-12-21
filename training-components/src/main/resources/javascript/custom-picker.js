function getCustomPickerData() {
    const customPickerFrame = document.getElementById("customPicker");
    if (!customPickerFrame || !customPickerFrame.contentWindow || !customPickerFrame.contentWindow.customPickerData) {
        return undefined;
    }
    return customPickerFrame.contentWindow.customPickerData;
}


function pickerInit() {
    return $.parseHTML("<iframe id=\"customPicker\" style=\"position: absolute; height: 100%; border: none\" width=\"100%\" height=\"100%\" src=\"" + jahiaGWTParameters.baseUrl + "/sites/" + jahiaGWTParameters.siteKey + ".custom-picker.html\"/>")[0];
}

function pickerLoad(data) {
    const $data = data;
    let pickerData = getCustomPickerData();
    if (pickerData !== undefined) {
        pickerData.data = $data;
    } else {
        const customPickerFrame = document.getElementById("customPicker");
        if (customPickerFrame !== undefined && customPickerFrame.contentWindow !== undefined) {
            customPickerFrame.contentWindow.addEventListener("load", () => {
                pickerData = getCustomPickerData();
                if (pickerData !== undefined) {
                    pickerData.data = $data;
                }
            });
        }
    }
}

function pickerGet() {
    const pickerData = getCustomPickerData();
    if (pickerData !== undefined) {
        return pickerData.data;
    }
    return undefined;
}
