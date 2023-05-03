import React, {useEffect} from 'react';
import Codemirror from 'codemirror';
import 'codemirror/lib/codemirror.css';
import 'codemirror/theme/dracula.css';
import 'codemirror/mode/javascript/javascript';
import 'codemirror/mode/python/python';
import 'codemirror/mode/clike/clike';
import 'codemirror/addon/edit/closetag';
import 'codemirror/addon/edit/closebrackets';
import {sendChange} from "../requests/websocket/script/producer/sendScriptChange";
import {listenScriptChanges} from "../requests/websocket/script/consumer/scriptChange";

const CodeEditor = ({
        language,
        editorRef,
    }) => {

    useEffect(() => {
        async function init() {
            if(editorRef.current == null) {
                editorRef.current = Codemirror.fromTextArea(
                    document.getElementById('realtimeEditor'),
                    {
                        mode: { name: language, json: true },
                        theme: 'dracula',
                        autoCloseTags: true,
                        autoCloseBrackets: true,
                        lineNumbers: true,
                    }
                );
                editorRef.current.setSize('100%', '100%');
            } else {
                editorRef.current.setOption('mode', {name: language, json: true});
            }
        }
        init();
    }, [language]);

    return <textarea id="realtimeEditor" />;
};

export default CodeEditor;