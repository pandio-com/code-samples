namespace Consuming
{
    using DotPulsar;
    using DotPulsar.Abstractions;
    using DotPulsar.Extensions;
    using System;
    using System.Threading;
    using System.Threading.Tasks;

    internal static class Program
    {
        private static async Task Main()
        {
            const string myTopic = "persistent://public/default/my-go-topic";

            var cts = new CancellationTokenSource();

            Console.CancelKeyPress += (sender, args) =>
            {
                cts.Cancel();
                args.Cancel = true;
            };

            await using var client = PulsarClient.Builder()
                .ServiceUrl(new Uri("pulsar+ssl://<pulsarurl>:6651"))
                .AuthenticateUsingToken("<jwttoken>")
                .Build(); //Connecting to pulsar://localhost:6650

            await using var consumer = client.NewConsumer(Schema.String)
                .StateChangedHandler(Monitor)
                .SubscriptionName("MySubscription")
                .Topic(myTopic)
                .Create();

            Console.WriteLine("Press Ctrl+C to exit");

            await ConsumeMessages(consumer, cts.Token);
        }

        private static async Task ConsumeMessages(IConsumer<string> consumer, CancellationToken cancellationToken)
        {
            try
            {
                await foreach (var message in consumer.Messages(cancellationToken))
                {
                    Console.WriteLine("Received: " + message.Value());
                    await consumer.Acknowledge(message, cancellationToken);
                }
            }
            catch (OperationCanceledException) { }
        }

        private static void Monitor(ConsumerStateChanged stateChanged, CancellationToken cancellationToken)
        {
            var stateMessage = stateChanged.ConsumerState switch
            {
                ConsumerState.Active => "is active",
                ConsumerState.Inactive => "is inactive",
                ConsumerState.Disconnected => "is disconnected",
                ConsumerState.Closed => "has closed",
                ConsumerState.ReachedEndOfTopic => "has reached end of topic",
                ConsumerState.Faulted => "has faulted",
                _ => $"has an unknown state '{stateChanged.ConsumerState}'"
            };

            var topic = stateChanged.Consumer.Topic;
            Console.WriteLine($"The consumer for topic '{topic}' " + stateMessage);
        }
    }
}