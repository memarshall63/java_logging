echo "Sending: -d '{"severity":"AUDIT","logger":"splunklogger","time":"1601420016.818","thread":"main","message":{"@ts":"2020-10-30T12:15:30.0123456Z","msg":"AUDIT: This message must be written to the logs, no exceptions. It has a 100 Level - same level as Halloween"}}'"
curl -k https://10.0.2.4:8088/services/collector/raw?sourcetype=new_json \
-H "Authorization: Splunk 2cd18bfd-3656-4881-acc3-752a49257002" \
-d '{"severity":"AUDIT","logger":"splunklogger","time":"1601420016.818","thread":"main","message":{"@ts":"2020-10-30T12:15:30.0123456Z","msg":"AUDIT: This message must be written to the logs, no exceptions. It has a 100 Level - same level as Halloween"}}'
echo
