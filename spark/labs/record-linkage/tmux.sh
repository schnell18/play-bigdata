if ! tmux has-session -t spark; then
    tmux new -s spark -d
    tmux new-window -t spark:1 nvim
    tmux new-window -t spark:2 spark-shell --master spark://primary.spark.vn:7077
    tmux split-window -t spark:2 -h spark-sql --master spark://primary.spark.vn:7077
    # tmux send-keys -t spark:2.1 Enter
    # tmux send-keys -t spark:2.2 spark-shell Enter
fi
tmux attach -t spark
