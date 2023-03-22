import React, {useEffect, useRef} from 'react';
import Codemirror from 'codemirror';
import 'codemirror/lib/codemirror.css';
import 'codemirror/theme/dracula.css';
import 'codemirror/mode/javascript/javascript';
import 'codemirror/mode/python/python';
import 'codemirror/mode/clike/clike';
import 'codemirror/addon/edit/closetag';
import 'codemirror/addon/edit/closebrackets';

const CodeEditor = ({
        // roomId,
        language,
        code,
    }) => {

    const editorRef = useRef(null);

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
                editorRef.current.setValue(code);
            } else {
                editorRef.current.setOption('mode', {name: language, json: true});
                editorRef.current.setValue(code);
            }
        }
        init();
    }, [language]);

    return <textarea id="realtimeEditor" />;
};

export default CodeEditor;