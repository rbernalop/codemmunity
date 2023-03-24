import { useState } from 'react';
import {Input} from "antd";
import {renameScript} from "../../requests/scripts/renameScript";
import {errorNotification} from "../../utils/notification";

const EditScriptTitle = ({id, title, setTitle}) => {
    const [editing, setEditing] = useState(false);

    const rename = async (scriptId, newName) => {
        renameScript(scriptId, newName).then(() =>
            setTitle(newName)
        ).catch(e =>
            errorNotification("Error while renaming script", e.response.data.message || "Try again later", "topRight")
        );
    }

    const handleClick = () => {
        setEditing(true);
    };

    const handleBlur = (event) => {
        rename(id, event.target.value);
        setEditing(false);
    }

    const handleChange = (event) => {
        setTitle(event.target.value);
    };

    const handleKeyDown = (event) => {
        if (event.key === 'Enter')
            handleBlur(event)

    }

    return (
    <h1 onClick={handleClick} onBlur={handleBlur}>
        {editing ? (
            <Input type="text"
               value={title}
               onChange={handleChange}
               style={{width: '100%', height: '100%', padding: 0, margin: 0, border: 'none', outline: 'none', fontSize: '1.5rem', fontWeight: 'bold'}}
               autoFocus={true}
               onKeyDown={handleKeyDown}
            />
        ) : (
            title
        )}
    </h1>
    );
}

export default EditScriptTitle;
