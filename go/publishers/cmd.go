package main

import (
	"github.com/spf13/cobra"
)

func CommandDef(cfg AppConfig) {
	rootCmd := &cobra.Command{
		Use: "publishers",
	}

	singleTopicCmd := &cobra.Command{
		Use: "single-topic",
		Run: func(cmd *cobra.Command, args []string) {
			withSingleTopic(cfg)
		},
	}

	multipleTopicCmd := &cobra.Command{
		Use: "multiple-topic",
		Run: func(cmd *cobra.Command, args []string) {
			withMultipleTopic(cfg)
		},
	}

	createMultipleTopicCmd := &cobra.Command{
		Use: "create-topics",
		Run: func(cmd *cobra.Command, args []string) {
			createMultipleTopic()
		},
	}


	rootCmd.AddCommand(singleTopicCmd, multipleTopicCmd, createMultipleTopicCmd)

	rootCmd.Execute()
}
