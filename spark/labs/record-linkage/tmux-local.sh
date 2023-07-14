if ! tmux has-session -t spark; then
    tmux new -s spark -d
    tmux new-window -t spark:1 spark-shell --master local[*]
    tmux split-window -t spark:1 -h htop
    tmux split-window -t spark:1 -v vmstat 1
    tmux select-pane -t spark:1.0
fi
tmux attach -t spark
