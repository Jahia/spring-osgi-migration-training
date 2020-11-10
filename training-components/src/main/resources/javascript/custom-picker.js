function getCustomPickerData() {
    var customPickerFrame = document.getElementById("customPicker");
    if (!customPickerFrame || !customPickerFrame.contentWindow || !customPickerFrame.contentWindow.customPickerData) {
        return undefined;
    }
    return customPickerFrame.contentWindow.customPickerData;
}


function pickerInit(data) {
    return $.parseHTML("<iframe id=\"customPicker\" style=\"border: 0\" width=\"100%\" height=\"100%\" src=\"" + jahiaGWTParameters.baseUrl + "/sites/" + jahiaGWTParameters.siteKey + ".custom-picker.html\"/>")[0];
}

function pickerLoad(data) {
    var pickerData = getCustomPickerData();
    var $data = data;
    if (pickerData !== undefined) {
        pickerData.load(data);
    } else {
        var customPickerFrame = document.getElementById("customPicker");
        if (customPickerFrame !== undefined && customPickerFrame.contentWindow !== undefined) {
            customPickerFrame.contentWindow.addEventListener("load", function (event) {
                pickerData = getCustomPickerData();
                if (pickerData !== undefined) {
                    pickerData.load($data);
                }
            });
        }
    }
}

function pickerGet() {
    var pickerData = getCustomPickerData();
    if (pickerData !== undefined) {
        return pickerData.get();
    }
    return undefined;
}
